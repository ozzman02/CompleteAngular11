using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace MvcTaskManager.Controllers
{
    /* Controller for serving home/index page at startup */
    public class HomeController : Controller
    {
        [Route("")]
        [Route("home/index")]
        public IActionResult Index()
        {
            return View();
        }
    }
}



