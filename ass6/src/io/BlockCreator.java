package io;

import sprites.Block;
/**
 * A BlockCreator interface.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     * @param xpos int
     * @param ypos int
     * @return block with xpos and ypos location
     */
    Block create(int xpos, int ypos);
 }