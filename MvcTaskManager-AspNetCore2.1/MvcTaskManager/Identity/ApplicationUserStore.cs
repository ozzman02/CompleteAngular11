using System;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;

namespace MvcTaskManager.Identity
{
    /* Extends UserStore class and provides methods for storing users information */
    public class ApplicationUserStore : UserStore<ApplicationUser>
    {
        public ApplicationUserStore(ApplicationDbContext applicationDbContext) : base(applicationDbContext)
        {
        }
    }
}

