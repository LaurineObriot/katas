import System.Environment (getArgs)
import Diamond (diamond)

main = getArgs >>=
    putStr . unlines . diamond . head . head
