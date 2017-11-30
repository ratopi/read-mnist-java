package de.ratopi.mnist.read.io;

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
			mnistLabelProvider.selectNext();
			mnistImageProvider.selectNext();

			final byte item = mnistLabelProvider.getCurrentValue();
			System.out.println( i + " is a " + item );
			final BufferedImage image = mnistImageProvider.getCurrentImage();
			ImageIO.write( image, "png", new File( "output/image" + item + "." + i + ".png" ) );
			i++;
			if ( i > 99 ) System.exit( 0 );
		}
	}
}
