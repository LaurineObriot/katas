using System.Collections.Generic;
using System.Linq;

namespace DiamondKata
{
    public static class EnumerableExtensions
    {
        public static IEnumerable<T> Mirror<T>(this IEnumerable<T> items)
        {
            IReadOnlyCollection<T> allItems = items.ToList();
            var reversedItems = allItems.Reverse();
            return allItems.Concat(reversedItems.Skip(1));
        }

        public static string Join(this IEnumerable<char> items) => new string(items.ToArray());
    }
}
