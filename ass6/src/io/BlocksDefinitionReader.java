package io;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

/**
 * A BlocksDefinitionReader class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class BlocksDefinitionReader {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * @param spacerWidths
     * @param blockCreators
     */
    public BlocksDefinitionReader() {
        this.spacerWidths = new TreeMap<>();
        this.blockCreators = new TreeMap<>();
    }

    /**
     * @param reader reader.
     * @return BlocksFromSymbolsFactory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksFromSymbolsFactory blockFactory = new BlocksFromSymbolsFactory();

        Map<String, Integer> spacerWidths = null;
        Map<String, BlockCreator> blockCreators = null;
        String thisLine;
        BufferedReader is = null;
        is = new BufferedReader(reader);
        try {
            Map<String, String> defultMap = new TreeMap<String, String>();
            Map<String, String> help = new TreeMap<String, String>();
            Map<String, String> blockMap = new TreeMap<String, String>();

            while ((thisLine = is.readLine()) != null) {
                thisLine = thisLine.trim();
                if (!thisLine.equals("") && !thisLine.startsWith("#")) {
                    if (thisLine.startsWith("default")) {
                        thisLine = thisLine.substring("default".length()).trim();
                        defultMap = parseLine(thisLine);
                    } else if (thisLine.startsWith("bdef")) {
                        thisLine = thisLine.substring("bdef".length()).trim();
                        help.clear();
                        help = parseLine(thisLine);
                        blockMap.clear();
                        blockMap.putAll(defultMap);
                        blockMap.putAll(help);
                        if (blockMap.containsKey("symbol") && blockMap.containsKey("width")
                                && blockMap.containsKey("height") && blockMap.containsKey("hit_points")) {
                            String symbol = blockMap.get("symbol");
                            BlocksMaker block = new BlocksMaker();
                            block.setWidth(Integer.valueOf(blockMap.get("width")));
                            block.setHeight(Integer.valueOf(blockMap.get("height")));
                            block.setHitPoints(Integer.valueOf(blockMap.get("hit_points")));
                            if (blockMap.containsKey("stroke")) {
                                Color p = ColorsParser.colorFromString(blockMap.get("stroke"));
                                block.setStrokeColor(p);
                            }
                            Map<Integer, Color> colors = new TreeMap<Integer, Color>();
                            Map<Integer, Image> images = new TreeMap<Integer, Image>();
                            if (blockMap.containsKey("fill")) {
                                blockMap.put("fill-1", blockMap.get("fill"));
                                blockMap.remove("fill");
                            }
                            for (int i = 0; i <= Integer.valueOf(blockMap.get("hit_points")); i++) {
                                if (blockMap.containsKey("fill-" + i)) {
                                    String fillString = blockMap.get("fill-" + i);
                                    if (fillString.startsWith("image(")) {
                                        fillString = fillString.substring("image(".length());
                                        fillString = fillString.replace(")", "");
                                        InputStream imageIS = ClassLoader.getSystemClassLoader()
                                                .getResourceAsStream(fillString);
                                        Image image1 = null;
                                        try { // trying reading the image
                                            image1 = ImageIO.read(imageIS);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } // put the image in the map
                                        if (image1 != null) {
                                            images.put(i, image1);
                                        }
                                    } else if (fillString.startsWith("color(")) {
                                        Color color = ColorsParser.colorFromString(fillString);
                                        colors.put(i, color);
                                    } else {
                                        try {
                                            throw new Exception();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                            block.setFillBlockColor(colors);
                            block.setFillBlockImage(images);
                            blockFactory.putBlock(symbol, block);
                        } else {
                            try {
                                throw new Exception();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (thisLine.startsWith("sdef")) {
                        thisLine = thisLine.substring("sdef".length()).trim();
                        Map<String, String> spacerMap = parseLine(thisLine);
                        if (spacerMap.containsKey("symbol") && spacerMap.containsKey("width")) {
                            String symbol1 = spacerMap.get("symbol");
                            Integer width = Integer.valueOf(spacerMap.get("width"));
                            blockFactory.addSpaceWidth(symbol1, width);
                        } else {
                            try {
                                throw new Exception();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return blockFactory;
    }

    /**
     * @param thisLine string
     * @return map that split the line to key and value
     */
    private static Map<String, String> parseLine(String thisLine) {
        Map<String, String> maps = new TreeMap<String, String>();

        String[] pairs = thisLine.trim().split(" ");
        for (String pair : pairs) {
            String[] parts = pair.split(":");
            if (parts.length != 2) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("ooops");
                    e.printStackTrace();
                }
            }
            maps.put(parts[0], parts[1]);
        }
        return maps;
    }

}