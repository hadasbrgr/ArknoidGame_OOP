package io;

import java.util.Map;
import java.util.TreeMap;

import sprites.Block;

/**
 * A BlocksFromSymbolsFactory class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor.
     * @param spacerWidths Map<String, Integer>
     * @param blockCreators Map<String, BlockCreator>
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * constructs a new BlocksFromSymbolsFactory object.
     */
    public BlocksFromSymbolsFactory() {
        this.blockCreators = new TreeMap<String, BlockCreator>();
        this.spacerWidths = new TreeMap<String, Integer>();
    }

    /**
     * @param s String
     * @return true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);

    }

    /**
     * @param s String
     * @return true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);

    }

    /**
     * The block will be located at position (xpos, ypos).
     * @param s String
     * @param xpos x position
     * @param ypos y position
     * @return a block according to the definitions associated // with symbol s.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        Block b = this.blockCreators.get(s).create(xpos, ypos);
        return b;
    }

    /**
     * @param s String
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);

    }

    /**
     * add Space Width.
     * @param key string
     * @param value int
     */
    public void addSpaceWidth(String key, Integer value) {
        this.spacerWidths.put(key, value);
    }

    /**
     * add Block Creators.
     * @param key string
     * @param value BlockCreator
     */
    public void addBlockCreators(String key, BlockCreator value) {
        this.blockCreators.put(key, value);
    }

    /**
     * put block.
     * @param symbol String
     * @param block BlocksMaker
     */
    public void putBlock(String symbol, BlocksMaker block) {
        this.blockCreators.put(symbol, block);
    }

}
