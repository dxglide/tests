package dxglide.projects.gzipreader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * Created by dxglide on 17.2.2.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length != 1)
        {
            System.out.println("Please enter filename");
            return;
        }

        // open the input (compressed) file.
        FileInputStream stream = new FileInputStream(args[0]);
        FileOutputStream output = null;
        try
        {
            // open the gziped file to decompress.
            GZIPInputStream gzipstream = new GZIPInputStream(stream);
            byte[] buffer = new byte[2048];

            // create the output file without the .gz extension.
            String outname = args[0].substring(0, args[0].length()-3);
            output = new FileOutputStream(outname);

            // and copy it to a new file
            int len;
            while((len = gzipstream.read(buffer ))>0)
            {
                output.write(buffer, 0, len);
            }
        }
        finally
        {
            // both streams must always be closed.
            if(output != null) output.close();
            stream.close();
        }
    }
}
