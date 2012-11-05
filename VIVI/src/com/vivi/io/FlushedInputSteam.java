package com.vivi.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FlushedInputSteam extends FilterInputStream {
	
	public FlushedInputSteam(InputStream is) {
		super(is);
	}
	
	@Override
	public long skip(long n) throws IOException {
		long totalByteSkipped = 0L;
		
		while (totalByteSkipped < n) {
			long byteSkipped = in.skip(n - totalByteSkipped);
			
			if (byteSkipped == 0L) {
				int b = read();
				
				if (b < 0)
					break;	// we reached EOF
				else
					byteSkipped = 1;	// we read one byte 
			}
			totalByteSkipped += byteSkipped;
		}
		return totalByteSkipped;
	}
}
