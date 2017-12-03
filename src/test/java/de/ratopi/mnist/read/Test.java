package de.ratopi.mnist.read;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test
{
	public static void main( final String[] args ) throws IOException
	{
		final MnistReader trainMnistReader = new MnistReader( new File( "data/t10k-labels-idx1-ubyte.gz" ), new File( "data/t10k-images-idx3-ubyte.gz" ) );
		createImages( trainMnistReader, "train" );

		final MnistReader testMnistReader = new MnistReader( new File( "data/train-labels-idx1-ubyte.gz" ), new File( "data/train-images-idx3-ubyte.gz" ) );
		createImages( testMnistReader, "test" );
	}

	private static void createImages( final MnistReader mnistReader, final String prefix ) throws IOException
	{
		final File outputDir = new File( "/tmp/output" );
		if ( !outputDir.isDirectory() && !outputDir.mkdirs() ) throw new IOException( "Could not create outputdirectory" );

		mnistReader.handleAllRemaining(
				new MnistReader.DataArrayImageHandler()
				{
					@Override
					public void handle( final long index, final byte[] data, final byte item )
					{
						if ( index % 1000 == 0 ) System.out.println( index );
						try
						{
							final BufferedImage image = mnistReader.getDataAsBufferedImage( data );

							ImageIO.write( image, "png", new File( outputDir.getPath() + "/" + prefix + "." + item + "." + index + ".png" ) );
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
