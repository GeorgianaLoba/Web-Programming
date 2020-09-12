using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using lab8.Repositories;

namespace lab8.Controllers
{
    public class LoginController : Controller
    {
        // GET: Login
        public ActionResult Index()
        {
            return View("Login");
        }

        public string LoginUser()
        {
            var userName = Request.Params["username"];
            var userPassword = Request.Params["password"];
            var repo = new UsersRepository();
            var loginUser = repo.GetUser(userName, userPassword);
            if (loginUser == null)
            {
                return "error";
            }

            return "successfully logged in";
        }
    }
}