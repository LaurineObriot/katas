module Diamond
where
import Data.List (inits)

diamond :: Char -> [String]
diamond l = mirror (map mirror diagonal)
    where
    diagonal = (reverse spaces) <+> (letters <:> spaces)
    letters  = ['A'..l]
    spaces   = take (length letters) (inits (repeat ' '))
    (<+>)    = zipWith (++)
    (<:>)    = zipWith (:)
    mirror l = l ++ (tail (reverse l))
