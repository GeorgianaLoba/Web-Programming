using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace lab8.Models
{
    public class Document
    {
        public int Id { get; set; }
        public string Author { get; set; }
        public string Title { get; set; }
        public int NumberPages { get; set; }
        public string Type { get; set; }
        public string Format { get; set; }
    }
}