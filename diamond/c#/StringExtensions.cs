namespace DiamondKata
{
    public static class StringExtensions
    {
        public static string Mirror(this string @this) =>
            @this.ToCharArray()
                 .Mirror()
                 .Join();
    }
}
