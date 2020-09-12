using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using lab8.Repositories;
using System.Text.Json;
using System.Web.Script.Serialization;
using lab8.Models;
using Microsoft.Ajax.Utilities;
using Newtonsoft.Json;


namespace lab8.Controllers
{
    public class DocumentsController : Controller
    {
        // GET: Documents
        public ActionResult Index()
        {
            return View("Documents");
        }

        public string FetchData()
        {
            var repo = new DocumentsRepository();
            List<Document> docs = repo.GetAllDocuments();
            // return JsonSerializer.Serialize(repo.GetAllDocuments());
            JavaScriptSerializer jss = new JavaScriptSerializer();
            return jss.Serialize(docs);
        }

        public void DeleteData()
        {
            var id = Convert.ToInt32(Request.Params["id"]);
            var repo = new DocumentsRepository();
            repo.DeleteDocument(id);
        }

        public void AddData()
        {
            var author = Request.Params["author"];
            var title = Request.Params["title"];
            var numberPages = Request.Params["numberPages"];
            var format = Request.Params["format"];
            var type = Request.Params["type"];
            var repo = new DocumentsRepository();
            var document = new Document
            {
                Author = author,
                Title = title,
                NumberPages = Convert.ToInt32(numberPages),
                Format = format,
                Type = type,
                Id = 0
            };
            repo.SaveDocument(document);
        }
        public void UpdateData()
        {
            var id = Convert.ToInt32(Request.Params["id"]);
            var author = Request.Params["author"];
            var title = Request.Params["title"];
            var numberPages = Request.Params["numberPages"];
            var format = Request.Params["format"];
            var type = Request.Params["type"];
            var repo = new DocumentsRepository();
            var document = new Document
            {
                Author = author,
                Title = title,  
                NumberPages = Convert.ToInt32(numberPages),
                Format = format,
                Type = type,
                Id = 0
            };
            repo.UpdateDocument(id, document);
        }


        public string FetchFiltered()
        {
            var type = Request.Params["type"];
            var repo = new DocumentsRepository();
            List<Document> docs = repo.GetAllFiltered(type);
            // return JsonSerializer.Serialize(repo.GetAllDocuments());
            JavaScriptSerializer jss = new JavaScriptSerializer();
            return jss.Serialize(docs);
        }
    }

}