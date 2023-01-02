using System;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;

namespace MvcTaskManager.Identity
{
    /* Extends RoleStore class and provides methods for storing user roles information */
    public class ApplicationRoleStore : RoleStore<ApplicationRole, ApplicationDbContext>
    {
        public ApplicationRoleStore(
            ApplicationDbContext applicationDbContext,
            IdentityErrorDescriber identityErrorDescriber) : base(applicationDbContext, identityErrorDescriber)
        {

        }
    }
}

