package de.ratopi.mnist.read;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Test
{
	public static void main( final String[] args ) throws IOException
	{
		new File( "output" ).mkdir();

		final MnistReader mnistReader = new MnistReader(
				new File( "data/t10k-labels-idx1-ubyte.gz" ),
				new File( "data/t10k-images-idx3-ubyte.gz" )
		);

		mnistReader.handleAllRemaining(
				new MnistReader.BufferedImageHandler()
				{
					@Override
					public void handle( long index, BufferedImage image, byte item )
					{
						if (index % 1000 == 0) System.out.println( index );
						try
						{
							ImageIO.write( image, "png", new File( "output/image." + item + "." + index + ".png" ) );
						}
						catch ( IOException e )
						{
							throw new RuntimeException( e );
						}
					}
			}
		);

		mnistReader.close();
	}
}
