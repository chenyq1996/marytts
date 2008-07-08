/**
 * Copyright 2004-2006 DFKI GmbH.
 * All Rights Reserved.  Use is subject to license terms.
 * 
 * Permission is hereby granted, free of charge, to use and distribute
 * this software and its documentation without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of this work, and to
 * permit persons to whom this work is furnished to do so, subject to
 * the following conditions:
 * 
 * 1. The code must retain the above copyright notice, this list of
 *    conditions and the following disclaimer.
 * 2. Any modifications must be clearly marked as such.
 * 3. Original authors' names are not deleted.
 * 4. The authors' names are not used to endorse or promote products
 *    derived from this software without specific prior written
 *    permission.
 *
 * DFKI GMBH AND THE CONTRIBUTORS TO THIS WORK DISCLAIM ALL WARRANTIES WITH
 * REGARD TO THIS SOFTWARE, INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS, IN NO EVENT SHALL DFKI GMBH NOR THE
 * CONTRIBUTORS BE LIABLE FOR ANY SPECIAL, INDIRECT OR CONSEQUENTIAL
 * DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR
 * PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS
 * ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
 * THIS SOFTWARE.
 */

package marytts.signalproc.display;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import marytts.util.math.MathUtils;



/**
 * @author Marc Schr&ouml;der
 *
 */
public class LogSpectrum  extends SignalSpectrum
{
    public LogSpectrum(AudioInputStream ais)
    {
        this(ais, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
            
    public LogSpectrum(AudioInputStream ais, int width, int height)
    {
        super(ais, width, height);
    }

    public LogSpectrum(final double[] signal, int samplingRate)
    {
        this(signal, samplingRate, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public LogSpectrum(final double[] signal, int samplingRate, int width, int height)
    {
        super(signal, samplingRate, width, height);
    }

    /**
     * Compute log spectrum.
     * @param freqs the frequencies that come out of the FFT.
     */
    protected void process(double[] freqs)
    {
        for (int i=0; i<freqs.length; i++) {
            // convert frequency amplitudes into energy, then into db.
            freqs[i] = MathUtils.db(freqs[i]*freqs[i]);
        }
    }
    
    public static void main(String[] args) throws Exception
    {
        for (int i=0; i<args.length; i++) {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(args[i]));
            LogSpectrum signalSpectrum = new LogSpectrum(ais);
            signalSpectrum.showInJFrame(args[i], true, false);
        }
    }
    
}