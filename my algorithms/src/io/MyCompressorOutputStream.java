package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {


	private OutputStream out;


	/**
	 * Instantiates a new my compressor output stream.
	 *
	 * @param out the out
	 */
	public MyCompressorOutputStream(OutputStream out)
	{
		super();
		this.out = out;
	}


	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int b) throws IOException 
	{
		out.write(b);
	}


	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(byte[])
	 */
	@Override
	public void write(byte [] b) throws IOException
	{
		write(b.length);
		int counter = 1;
		byte previousByte = b[0];
		for (int i = 1; i < b.length; i++) 
		{
			if (b.length > 1) 
			{
				if (b[i] != previousByte) 
				{
					out.write(previousByte);
					out.write(counter);
					counter = 1;
					previousByte = b[i];
				}
				else
				{
					if (b[i] == previousByte) 
					{
						counter++;
					}
				}
			}

			if (b.length == 1)
			{
				out.write(previousByte);
				out.write(counter);
				out.write(b[i]);
				out.write(1);
				return;
			}
		}
	}
}