<?php
/**
 * Created by PhpStorm.
 * User: jonatan
 * Date: 04/12/2018
 * Time: 17:01
 */

namespace App\Model;


class Cell
{


    private $isAlive;

    private $generation;

    protected $posX;
    protected $posY;

    const DEAD = false;
    const ALIVE = true;



    public function __construct($isAlive = false,$posX=0,$posY=0)
    {
        $this->isAlive = $isAlive;
        $this->posX = $posX;
        $this->posY = $posY;
        $this->generation = 1;
    }

    /**
     * @return int
     */
    public function getPosX(): int
    {
        return $this->posX;
    }

    /**
     * @return int
     */
    public function getPosY(): int
    {
        return $this->posY;
    }

    /**
     * @return Boolean
     */
    public function isAlive()
    {
        return $this->isAlive;
    }


    public function nextGeneration(Neighbours $neighbours)
    {
        $count = $neighbours->alive();

        $options =
            [
                0 => self::DEAD,
                1 => self::DEAD,
                2 => $this->isAlive(),
                3 => self::ALIVE,
                4 => self::DEAD,
                5 => self::DEAD,
                6 => self::DEAD,
                7 => self::DEAD,
                8 => self::DEAD
            ];


        $alive = $options[$count];

        $cell = clone($this);
        $cell->isAlive = $alive;
        $cell->generation++;

        return $cell;

    }








}