package rr.drawfuns;

import i.IDoomSystem;
import static m.fixed_t.FRACBITS;

public abstract class R_DrawTranslatedColumnLow<T, V>
        extends DoomColumnFunction<T, V> {

    public R_DrawTranslatedColumnLow(int SCREENWIDTH, int SCREENHEIGHT,
            int[] ylookup, int[] columnofs, ColVars<T, V> dcvars, V screen,
            IDoomSystem I) {
        super(SCREENWIDTH, SCREENHEIGHT, ylookup, columnofs, dcvars, screen, I);
        this.flags = DcFlags.TRANSLATED | DcFlags.LOW_DETAIL;
    }

    public static final class HiColor
            extends R_DrawTranslatedColumnLow<byte[], short[]> {

        public HiColor(int SCREENWIDTH, int SCREENHEIGHT, int[] ylookup,
                int[] columnofs, ColVars<byte[], short[]> dcvars,
                short[] screen, IDoomSystem I) {
            super(SCREENWIDTH, SCREENHEIGHT, ylookup, columnofs, dcvars,
                    screen, I);
        }

        public void invoke() {
            int count;
            // MAES: you know the deal by now...
            int dest, dest2;
            int frac;
            final int fracstep;
            final int dc_source_ofs = dcvars.dc_source_ofs;
            final byte[] dc_source = dcvars.dc_source;
            final short[] dc_colormap = dcvars.dc_colormap;
            final byte[] dc_translation = dcvars.dc_translation;

            count = dcvars.dc_yh - dcvars.dc_yl;
            if (count < 0) {
                return;
            }

            if (RANGECHECK) {
                super.performRangeCheck();
            }

            // The idea is to draw more than one pixel at a time.
            dest = blockyDest1();
            dest2 = blockyDest2();

            // Looks familiar.
            fracstep = dcvars.dc_iscale;
            frac
                    = dcvars.dc_texturemid + (dcvars.dc_yl - dcvars.centery)
                    * fracstep;

            // Here we do an additional index re-mapping.
            // Maes: Unroll by 4
            if (count >= 4) {
                do {
                    // Translation tables are used
                    // to map certain colorramps to other ones,
                    // used with PLAY sprites.
                    // Thus the "green" ramp of the player 0 sprite
                    // is mapped to gray, red, black/indigo.
                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;
                    frac += fracstep;

                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;
                    frac += fracstep;

                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;
                    frac += fracstep;

                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;
                    frac += fracstep;

                } while ((count -= 4) > 4);
            }

            if (count > 0) {
                do {
                    // Translation tables are used
                    // to map certain colorramps to other ones,
                    // used with PLAY sprites.
                    // Thus the "green" ramp of the player 0 sprite
                    // is mapped to gray, red, black/indigo.
                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;

                    frac += fracstep;
                } while (count-- != 0);
            }
        }

    }

    public static final class Indexed
            extends R_DrawTranslatedColumnLow<byte[], byte[]> {

        public Indexed(int SCREENWIDTH, int SCREENHEIGHT, int[] ylookup,
                int[] columnofs, ColVars<byte[], byte[]> dcvars, byte[] screen,
                IDoomSystem I) {
            super(SCREENWIDTH, SCREENHEIGHT, ylookup, columnofs, dcvars,
                    screen, I);
        }

        public void invoke() {
            int count;
            // MAES: you know the deal by now...
            int dest, dest2;
            int frac;
            final int fracstep;
            final int dc_source_ofs = dcvars.dc_source_ofs;
            final byte[] dc_source = dcvars.dc_source;
            final byte[] dc_colormap = dcvars.dc_colormap;
            final byte[] dc_translation = dcvars.dc_translation;

            count = dcvars.dc_yh - dcvars.dc_yl;
            if (count < 0) {
                return;
            }

            if (RANGECHECK) {
                super.performRangeCheck();
            }

            // The idea is to draw more than one pixel at a time.
            dest = blockyDest1();
            dest2 = blockyDest2();

            // Looks familiar.
            fracstep = dcvars.dc_iscale;
            frac
                    = dcvars.dc_texturemid + (dcvars.dc_yl - dcvars.centery)
                    * fracstep;

            // Here we do an additional index re-mapping.
            // Maes: Unroll by 4
            if (count >= 4) {
                do {
                    // Translation tables are used
                    // to map certain colorramps to other ones,
                    // used with PLAY sprites.
                    // Thus the "green" ramp of the player 0 sprite
                    // is mapped to gray, red, black/indigo.
                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;
                    frac += fracstep;

                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;
                    frac += fracstep;

                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;
                    frac += fracstep;

                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;
                    frac += fracstep;

                } while ((count -= 4) > 4);
            }

            if (count > 0) {
                do {
                    // Translation tables are used
                    // to map certain colorramps to other ones,
                    // used with PLAY sprites.
                    // Thus the "green" ramp of the player 0 sprite
                    // is mapped to gray, red, black/indigo.
                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;

                    frac += fracstep;
                } while (count-- != 0);
            }
        }
    }

    public static final class TrueColor
            extends R_DrawTranslatedColumnLow<byte[], int[]> {

        public TrueColor(int SCREENWIDTH, int SCREENHEIGHT, int[] ylookup,
                int[] columnofs, ColVars<byte[], int[]> dcvars, int[] screen,
                IDoomSystem I) {
            super(SCREENWIDTH, SCREENHEIGHT, ylookup, columnofs, dcvars,
                    screen, I);
        }

        public void invoke() {
            int count;
            // MAES: you know the deal by now...
            int dest, dest2;
            int frac;
            final int fracstep;
            final int dc_source_ofs = dcvars.dc_source_ofs;
            final byte[] dc_source = dcvars.dc_source;
            final int[] dc_colormap = dcvars.dc_colormap;
            final byte[] dc_translation = dcvars.dc_translation;

            count = dcvars.dc_yh - dcvars.dc_yl;
            if (count < 0) {
                return;
            }

            if (RANGECHECK) {
                super.performRangeCheck();
            }

            // The idea is to draw more than one pixel at a time.
            dest = blockyDest1();
            dest2 = blockyDest2();

            // Looks familiar.
            fracstep = dcvars.dc_iscale;
            frac
                    = dcvars.dc_texturemid + (dcvars.dc_yl - dcvars.centery)
                    * fracstep;

            // Here we do an additional index re-mapping.
            // Maes: Unroll by 4
            if (count >= 4) {
                do {
                    // Translation tables are used
                    // to map certain colorramps to other ones,
                    // used with PLAY sprites.
                    // Thus the "green" ramp of the player 0 sprite
                    // is mapped to gray, red, black/indigo.
                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;
                    frac += fracstep;

                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;
                    frac += fracstep;

                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;
                    frac += fracstep;

                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;
                    frac += fracstep;

                } while ((count -= 4) > 4);
            }

            if (count > 0) {
                do {
                    // Translation tables are used
                    // to map certain colorramps to other ones,
                    // used with PLAY sprites.
                    // Thus the "green" ramp of the player 0 sprite
                    // is mapped to gray, red, black/indigo.
                    screen[dest]
                            = screen[dest2]
                            = dc_colormap[0x00FF & dc_translation[0xFF & dc_source[dc_source_ofs
                            + (frac >> FRACBITS)]]];
                    dest += SCREENWIDTH;
                    dest2 += SCREENWIDTH;

                    frac += fracstep;
                } while (count-- != 0);
            }
        }
    }

}
