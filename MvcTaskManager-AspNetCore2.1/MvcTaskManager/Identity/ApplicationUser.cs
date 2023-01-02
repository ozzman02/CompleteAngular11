using System;
using Microsoft.AspNetCore.Identity;
using System.ComponentModel.DataAnnotations.Schema;

namespace MvcTaskManager.Identity
{
    /* Extends IdentityUser class and acts as model class for users information */
    public class ApplicationUser : IdentityUser<String>
    {
        [NotMapped]
        public string Token { get; set; }
    }
}

