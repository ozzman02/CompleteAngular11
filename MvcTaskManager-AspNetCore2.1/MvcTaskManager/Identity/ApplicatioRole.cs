using System;
using Microsoft.AspNetCore.Identity;
using System.Collections.Generic;

namespace MvcTaskManager.Identity
{
    /* Extends IdentityRole class and acts as a model class for user roles information */
    public class ApplicationRole : IdentityRole<String>
    {
    }
}

