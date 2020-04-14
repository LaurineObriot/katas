<?php
/**
 * Created by PhpStorm.
 * User: jonatan
 * Date: 04/12/2018
 * Time: 19:04
 */

namespace App\Model;


/**
 * Class Neighbours
 * @package App\Model
 * @property Cell[] $neighbours
 */
class Neighbours
{

    private $neighbours = [];

    public function add(Cell $cell)
    {
        $this->neighbours[] = $cell ;
    }

    public function alive()
    {
        $alive = 0;
        foreach($this->neighbours as $cell){
            $alive += ($cell->isAlive() ? 1 : 0);

        }
        return $alive;
    }

    public function count()
    {
       return count($this->neighbours);
    }

}