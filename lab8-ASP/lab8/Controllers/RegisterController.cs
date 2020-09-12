using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using lab8.Repositories;

namespace lab8.Controllers
{
    public class RegisterController: Controller
    {

        public ActionResult Index()
        {
            return View("Register");
        }

        public string RegisterUser()
        {
            var userName = Request.Params["username"];
            var userPassword = Request.Params["password"];
            var repository = new UsersRepository();
            Boolean result = repository.RegisterUser(userName, userPassword);
            if (!result)
            {
                return "error";
            }
            return "successfully logged in";
        }
    }

}
