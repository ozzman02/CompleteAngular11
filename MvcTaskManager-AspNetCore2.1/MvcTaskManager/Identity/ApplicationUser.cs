using System;
using Microsoft.AspNetCore.Identity;
using System.ComponentModel.DataAnnotations.Schema;

namespace MvcTaskManager.Identity
{
    public class ApplicationUser : IdentityUser<String>
    {
        [NotMapped]
        public string Token { get; set; }
    }
}

