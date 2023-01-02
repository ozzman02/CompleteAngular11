using System;
using Microsoft.AspNetCore.Identity;
using Microsoft.Extensions.Logging;
using System.Collections.Generic;

namespace MvcTaskManager.Identity
{
    /* Extends RoleManager class and provides methods for manipulating user roles information */
    public class ApplicationRoleManager : RoleManager<ApplicationRole>
    {
        public ApplicationRoleManager(
            ApplicationRoleStore applicationRoleStore,
            IEnumerable<IRoleValidator<ApplicationRole>> roleValidators,
            ILookupNormalizer lookupNormalizer,
            IdentityErrorDescriber identityErrorDescriber,
            ILogger<ApplicationRoleManager> logger) : base(applicationRoleStore, roleValidators, lookupNormalizer, identityErrorDescriber, logger)
        {

        }
    }
}

