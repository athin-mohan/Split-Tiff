package com.Athin.SplitTiff;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.TIFFEncodeParam;
import com.sun.media.jai.codec.TIFFField;

public class SplitTiff {

	public static void main(String[] args) throws IOException {
/*
		System.out.println("Enter the dpi of image output(96/300)");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		@SuppressWarnings("unused")
		int dpi = input.nextInt();
		// new SplitTiff().doSplit(path,dpi);
		 * */
		int result=doSplitRange("C:\\Users\\atmohan\\Desktop\\TiffSplitterTest\\Tiff.tiff", "C:\\Users\\atmohan\\Desktop\\TiffSplitterTest", 300, 3, 5);
		System.out.println(result);
	}

	/* **********************************************************************************************/
	// Split all pages into single tiffs
	public static int doSplit(String path, String opath, int dpi) throws IOException {
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
	}

	/* **********************************************************************************************/

//Split the page range supplied into a single tiff

	public static int doSplitRange(String path, String opath, int dpi, int startPage, int endPage) throws IOException {
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
		List<RenderedImage> imageList = new ArrayList<RenderedImage>();
		for (int i = startPage - 1; i < endPage; i++) {
			RenderedImage page = dec.decodeAsRenderedImage(i);
			imageList.add(page);
		}
		File f = new File(opath + path.substring(path.lastIndexOf("\\"), path.indexOf(".")) + ".tif");
		// System.out.println("Saving " + f.getCanonicalPath());
		ParameterBlock pb = new ParameterBlock();
		pb.addSource(f);
		pb.add(f.toString());
		pb.add("tiff");
		param.setExtraImages(imageList.iterator());
		pb.add(param);
		RenderedOp r = JAI.create("filestore", pb, null);
		// System.out.println(r.getHeight());
		r.dispose();

		return count;
	}

}
