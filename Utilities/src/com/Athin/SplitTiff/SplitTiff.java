package com.Athin.SplitTiff;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.TIFFEncodeParam;
import com.sun.media.jai.codec.TIFFField;

public class SplitTiff {

	public static void main(String[] args) throws IOException {

		String result = doSplitRange("C:\\Users\\atmohan\\Desktop\\combine\\source\\test.tiff",
				"C:\\Users\\atmohan\\Desktop\\combine\\destination", 300, 3, 7);
		System.out.println(result);

		/*
		 * String result=combineTiffs(new String[]
		 * {"C:\\Users\\atmohan\\Desktop\\combine\\source\\1.tif",
		 * "C:\\Users\\atmohan\\Desktop\\combine\\source\\2.tif",
		 * "C:\\Users\\atmohan\\Desktop\\combine\\source\\3.tiff"}
		 * ,"C:\\Users\\atmohan\\Desktop\\combine\\destination",300);
		 * System.out.println(result);
		 */
	}

	/* **********************************************************************************************/
	// Split all pages into single tiffs
	public static int doSplit(String path, String opath, int dpi) throws IOException {

		try {
			FileInputStream ss = new FileInputStream(path);
			ImageDecoder dec = ImageCodec.createImageDecoder("tiff", ss, null);
			int count = dec.getNumPages();
			TIFFEncodeParam param = new TIFFEncodeParam();
			param.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);
			param.setLittleEndian(false);// Intel
			if (dpi == 300) {
				TIFFField[] extras = new TIFFField[2];
				extras[0] = new TIFFField(282, TIFFField.TIFF_RATIONAL, 1,
						(Object) new long[][] { { (long) 300, (long) 1 }, { (long) 0, (long) 0 } });
				extras[1] = new TIFFField(283, TIFFField.TIFF_RATIONAL, 1,
						(Object) new long[][] { { (long) 300, (long) 1 }, { (long) 0, (long) 0 } });

				param.setExtraFields(extras);
			}

			for (int i = 0; i < count; i++) {
				RenderedImage page = dec.decodeAsRenderedImage(i);

				File f = new File(opath + path.substring(path.lastIndexOf("\\"), path.indexOf(".")) + i + ".tif");
				// System.out.println("Saving " + f.getCanonicalPath());
				ParameterBlock pb = new ParameterBlock();
				pb.addSource(page);
				pb.add(f.toString());
				pb.add("tiff");
				pb.add(param);
				RenderedOp r = JAI.create("filestore", pb, null);
				// System.out.println(r.getHeight());
				r.dispose();

			}
			return count;

		} catch (Exception e) {
			return 0;
		}

	}

	/* **********************************************************************************************/

//Split the page range supplied into a single tiff

	public static String doSplitRange(String path, String opath, int dpi, int startPage, int endPage)
			throws IOException {

		try {
			FileInputStream ss = new FileInputStream(path);
			ImageDecoder dec = ImageCodec.createImageDecoder("tiff", ss, null);
			int count = dec.getNumPages();
			if (endPage > count)
				return "Endpage value greater than number of pages";
			else if (endPage < startPage)
				return "End Page cannot be smaller than start page";

			TIFFEncodeParam params = new TIFFEncodeParam();
			params.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);
			params.setLittleEndian(false);// Intel
			if (dpi == 300) {
				TIFFField[] extras = new TIFFField[2];
				extras[0] = new TIFFField(282, TIFFField.TIFF_RATIONAL, 1,
						(Object) new long[][] { { (long) 300, (long) 1 }, { (long) 0, (long) 0 } });
				extras[1] = new TIFFField(283, TIFFField.TIFF_RATIONAL, 1,
						(Object) new long[][] { { (long) 300, (long) 1 }, { (long) 0, (long) 0 } });

				params.setExtraFields(extras);
			}

			List<RenderedImage> imageList = new ArrayList<RenderedImage>();

			for (int i = startPage - 1; i < endPage; i++) {

				RenderedImage page = dec.decodeAsRenderedImage(i);
				imageList.add(page);
			}

			RenderedImage firstPage = imageList.get(0);
			imageList.remove(0);

			File f = new File(opath + path.substring(path.lastIndexOf("\\"), path.indexOf(".")) + startPage + "-"
					+ endPage + ".tif");

			OutputStream out = new FileOutputStream(f);
			ImageEncoder encoder = ImageCodec.createImageEncoder("tiff", out, params);
			params.setExtraImages(imageList.iterator());
			encoder.encode(firstPage);
			out.close();

		} catch (Exception e) {
			return "Error Occured while Splitting...";
		}
		return "Splitting Complteted Successfully";
	}

	/* Combine tifs *************************************************/
	public static String combineTiffs(String inputPaths[], String outputPath, int dpi) throws IOException {

		try {

			TIFFEncodeParam params = new TIFFEncodeParam();
			params.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);
			params.setLittleEndian(false);// Intel
			if (dpi == 300) {
				TIFFField[] extras = new TIFFField[2];
				extras[0] = new TIFFField(282, TIFFField.TIFF_RATIONAL, 1,
						(Object) new long[][] { { (long) 300, (long) 1 }, { (long) 0, (long) 0 } });
				extras[1] = new TIFFField(283, TIFFField.TIFF_RATIONAL, 1,
						(Object) new long[][] { { (long) 300, (long) 1 }, { (long) 0, (long) 0 } });

				params.setExtraFields(extras);
			}

			List<RenderedImage> imageList = new ArrayList<RenderedImage>();
			for (int i = 0; i < inputPaths.length; i++) {
				FileInputStream subDoc = new FileInputStream(inputPaths[i]);
				ImageDecoder decDoc = ImageCodec.createImageDecoder("tiff", subDoc, null);
				for (int j = 0; j < decDoc.getNumPages(); j++) {

					RenderedImage page = decDoc.decodeAsRenderedImage(j);
					imageList.add(page);

				}

			}
			RenderedImage firstPage = imageList.get(0);
			imageList.remove(0);

			File f = new File(outputPath + "\\" + "CombinedTif" + System.currentTimeMillis() + ".tif");

			OutputStream out = new FileOutputStream(f);
			ImageEncoder encoder = ImageCodec.createImageEncoder("tiff", out, params);

			params.setExtraImages(imageList.iterator());
			encoder.encode(firstPage);
			out.close();

		} catch (FileNotFoundException e) {
			return "One of the entered files is missing";
		} catch (Exception e) {
			return "Error Occurred while combining...";
		}

		return "Combining files completed";
	}
	/* ***********************************************************************/
}
