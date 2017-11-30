package de.ratopi.mnist.read;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
				new MnistReader.Handler()
				{
					@Override
					public void handle( long index, BufferedImage image, int item )
					{
						System.out.println( index );
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
	}
}
