package de.ratopi.mnist.read.io;

import de.ratopi.mnist.read.io.MnistImageProvider;
import de.ratopi.mnist.read.io.MnistLabelProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test
{
	public static void main( final String[] args ) throws IOException
	{
		new File("output").mkdir();

		final MnistImageProvider mnistImageProvider = new MnistImageProvider( new File( "data/t10k-images-idx3-ubyte.gz" ) );
		final MnistLabelProvider mnistLabelProvider = new MnistLabelProvider( new File( "data/t10k-labels-idx1-ubyte.gz" ) );

		int i = 0;
		while ( true )
		{
			final int item = mnistLabelProvider.nextItem();
			System.out.println( i + " is a " + item );
			final BufferedImage image = mnistImageProvider.nextImage();
			ImageIO.write( image, "png", new File( "output/image" + item + "." + i + ".png" ) );
			i++;
			if ( i > 99 ) System.exit( 0 );
		}
	}
}
