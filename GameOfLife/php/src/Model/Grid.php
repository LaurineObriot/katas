<?php
/**
 * Created by PhpStorm.
 * User: jonatan
 * Date: 04/12/2018
 * Time: 18:19
 */

namespace App\Model;


class Grid
{



    /**
     * @var Cell[][]
     */
    private $matrix;
    /**
     * @var int
     */
    private $rows;
    /**
     * @var int
     */
    private $columns;

    /**
     * @var int
     */
    private $generation;

    /**
     * Grid constructor.
     */
    public function __construct()
    {
        $this->matrix = [];
        $this->rows = 0;
        $this->columns = 0;
        $this->generation = 1;
    }

    /**
     * @return int
     */
    public function getGeneration(): int
    {
        return $this->generation;
    }

    /**
     * @param String $file
     *
     * @return Grid
     */
    public static function readFromString($string): Grid
    {
        $lines = explode("\n",$string);
        $dimensions = explode(" ",$lines[0]);
        $rows = intval($dimensions[0]);
        $columns = intval($dimensions[1]);

        if(count($lines) !== $rows +1 ){
            throw new \Exception("The number of rows is not correct");
        }

        $matrix= [];

        for($i=0;$i< $rows;$i++){
            $matrix[$i] = [];
            $cells = explode(" ",$lines[$i+1]);


            if(count($cells) !== $columns){
                throw new \Exception("The number of columns is not correct");
            }

            foreach($cells as $number=>$cellState){
                $matrix[$i][$number] = new Cell($cellState === '*',$i,$number);
            }
        }


        $grid = new Grid();

        $grid->matrix = $matrix;
        $grid->columns = $columns;
        $grid->rows = $rows;

        return $grid;

    }

    public static function factory($rows=1,$columns=1,$defaultState=Cell::ALIVE)
    {
        $matrix = [];
        for($x=0;$x< $rows;$x++){
            $matrix[$x] = [];
            for($y=0;$y<$columns;$y++){
                $matrix[$x][$y] = new Cell($defaultState,$x,$y);
            }
        }



        $grid = new Grid();
        $grid->matrix = $matrix;
        $grid->columns = $columns;
        $grid->rows = $rows;

        return $grid;


    }

    /**
     * @return float|int
     */
    public function count()
    {

        return $this->columns * $this->rows;
    }

    public function alive()
    {
        $cells = $this->getIterator();
        $alive = 0;
        foreach($cells as $cell){
            $alive += ($cell->isAlive() ? 1 : 0);
        }
        return $alive;
    }

    /**
     * @return Cell[]
     */
    public function getIterator(): iterable
    {

        for ($x = 0; $x < $this->rows; $x++) {
            for ($y = 0; $y < $this->columns; $y++) {
                yield $this->matrix[$x][$y];
            }
        }

    }



    public function toString()
    {

        $output = $this->rows. ' '. $this->columns;

        for($row=0; $row < $this->rows;$row++){
            $line = '';
            for($column =0;$column< $this->columns ; $column++){
                $line.= ($this->matrix[$row][$column]->isAlive() ? '*':'.') . ' ';
            }
            $output.= PHP_EOL . trim($line);
        }
        return $output;

    }

    public function getCell($x,$y):Cell
    {
        if(empty($this->matrix[$x][$y])){
            throw new \Exception("Cell not found");
        }

        return $this->matrix[$x][$y];
    }

    public function getNeighbours(Cell $cell):Neighbours
    {
        $positionsToSearch = [
            [
                'x'=> $cell->getPosX()-1,
                'y'=> $cell->getPosY()
            ],
            [
                'x'=> $cell->getPosX()+1,
                'y'=> $cell->getPosY()
            ],
            [
                'x'=> $cell->getPosX()-1,
                'y'=> $cell->getPosY()-1
            ],
            [
                'x'=> $cell->getPosX()-1,
                'y'=> $cell->getPosY()+1,
            ],
            [
                'x'=> $cell->getPosX(),
                'y'=> $cell->getPosY()+1,
            ],
            [
                'x'=> $cell->getPosX(),
                'y'=> $cell->getPosY()-1,
            ],
            [
                'x'=> $cell->getPosX()+1,
                'y'=> $cell->getPosY()+1,
            ],
            [
                'x'=> $cell->getPosX()+1,
                'y'=> $cell->getPosY()-1,
            ],
        ];

        $neighbours = new Neighbours();

        foreach($positionsToSearch as $position){
            try {
                $cell = $this->getCell($position['x'],$position['y']);
                $neighbours->add($cell);
            }catch (\Exception $e){}
        }



        return $neighbours;
    }

    public function nextGeneration()
    {
        $newMatrix=[];

        for($row=0; $row < $this->rows;$row++) {
            $newMatrix[$row]=[];
            for ($column = 0; $column < $this->columns; $column++) {
                $currentCell = $this->matrix[$row][$column];
                $newMatrix[$row][$column] = $currentCell->nextGeneration($this->getNeighbours($currentCell));
            }
        }

        $this->matrix = $newMatrix;


        $this->generation++;
    }


}