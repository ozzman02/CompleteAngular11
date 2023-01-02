﻿using System;
using MvcTaskManager.Identity;
using MvcTaskManager.ViewModels;
using System.Threading.Tasks;

namespace MvcTaskManager.ServiceContracts
{
    public interface IUsersService
    {
        Task<ApplicationUser> Authenticate(LoginViewModel loginViewModel);
    }
}

