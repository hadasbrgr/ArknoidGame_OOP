package io;

import java.awt.Color;

/**
 * A ColorsParser class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class ColorsParser {
    /**
     * parse color definition.
     * @param s string of color
     * @return the specified color.
     */
    public static java.awt.Color colorFromString(String s) {
        if (s.startsWith("color(")) {
            s = s.substring("color(".length());
            if (s.startsWith("RGB(")) {
                s = s.substring("RGB(".length());
                s = s.replace(")", "");
                String[] c = s.split(",");
                if (c.length != 3) {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    return new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]));
                }
            } else {
                s = s.replace(")", "");
                if (s.equals("blue")) {
                    return Color.blue;
                } else if (s.equals("black")) {
                    return Color.black;
                } else if (s.equals("cyan")) {
                    return Color.cyan;
                } else if (s.equals("gray")) {
                    return Color.gray;
                } else if (s.equals("lightGray")) {
                    return Color.lightGray;
                } else if (s.equals("green")) {
                    return Color.green;
                } else if (s.equals("orange")) {
                    return Color.orange;
                } else if (s.equals("pink")) {
                    return Color.pink;
                } else if (s.equals("red")) {
                    return Color.red;
                } else if (s.equals("white")) {
                    return Color.white;
                } else if (s.equals("yellow")) {
                    return Color.yellow;
                }
            }
        }
        return null;
    }
}
