package com.alectronic.kata.gameoflife.utils;

import com.alectronic.kata.gameoflife.World;

public class PrinterImpl implements Printer {

    @Override
    public void print(World world){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(world);
    }

}
