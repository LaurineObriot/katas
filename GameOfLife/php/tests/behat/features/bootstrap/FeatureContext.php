<?php

use App\Model\Grid;
use Behat\Behat\Context\Context;
use Behat\Gherkin\Node\PyStringNode;
use Behat\Gherkin\Node\TableNode;
use PHPUnit\Framework\Assert;

/**
 * Defines application features from the specific context.
 * @property Grid $grid
 */
class FeatureContext implements Context
{

    public $grid;
    /**
     * Initializes context.
     *
     * Every scenario gets its own context instance.
     * You can also pass arbitrary arguments to the
     * context constructor through behat.yml.
     */
    public function __construct()
    {
    }

    /**
     * @Given /^a grid with format:$/
     */
    public function aGridWithFormat(PyStringNode $string)
    {

        $this->grid = Grid::readFromString($string->getRaw());


    }

    /**
     * @When /^A generation happens$/
     */
    public function aGenerationHappens()
    {
        $this->grid->nextGeneration();
    }

    /**
     * @Then /^I have:$/
     */
    public function iHave(PyStringNode $string)
    {
        Assert::assertEquals($string->getRaw(),$this->grid->toString());
    }
}
