using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.CodeAnalysis;
using MvcTaskManager.Identity;
using MvcTaskManager.Models;
using Project = MvcTaskManager.Models.Project;

namespace MvcTaskManager.Controllers
{
    /* Controller for CRUD operations of Projects table */

    /* 
       In this case don't apply the [Authorize] filter for the entire ProjectsController.
       You have to apply the same individually for every action method because we cannot use Anti-Forgery
       and JWT Authentication at-a-time in the same action method.

       In the Startup.cs, we have enabled both JWT and Anti-Forgery but we can use either of those, in every action method.
    */
    public class ProjectsController : Controller
    {
        private ApplicationDbContext db;

        public ProjectsController(ApplicationDbContext db)
        {
            this.db = db;
        }

        [HttpGet]
        [Route("api/projects")]
        [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
        public List<Project> Get()
        {
            
            List<Project> projects = db.Projects.ToList();
            return projects;
        }

        [HttpGet]
        [Route("api/projects/search/{searchby}/{searchtext}")]
        [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
        public List<Project> Search(string searchBy, string searchText)
        {
            
            List<Project> projects = null;
            if (searchBy == "ProjectID" && searchText != "all")
                projects = db.Projects.Where(temp => temp.ProjectID.ToString().Contains(searchText)).ToList();
            else if (searchBy == "ProjectName" && searchText != "all")
                projects = db.Projects.Where(temp => temp.ProjectName.Contains(searchText)).ToList();
            else if (searchBy == "DateOfStart" && searchText != "all")
                projects = db.Projects.Where(temp => temp.DateOfStart.ToString().Contains(searchText)).ToList();
            else if (searchBy == "TeamSize" && searchText != "all")
                projects = db.Projects.Where(temp => temp.TeamSize.ToString().Contains(searchText)).ToList();
            else
                projects = db.Projects.ToList();

            return projects;
        }

        [HttpPost]
        [Route("api/projects")]
        [Authorize]
        [ValidateAntiForgeryToken]
        public Project Post([FromBody] Project project)
        {
            
            db.Projects.Add(project);
            db.SaveChanges();
            return project;
        }

        [HttpPut]
        [Route("api/projects")]
        [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
        public Project Put([FromBody] Project project)
        {
            
            Project existingProject = db.Projects.Where(temp => temp.ProjectID == project.ProjectID).FirstOrDefault();
            if (existingProject != null)
            {
                existingProject.ProjectName = project.ProjectName;
                existingProject.DateOfStart = project.DateOfStart;
                existingProject.TeamSize = project.TeamSize;
                db.SaveChanges();
                return existingProject;
            }
            else
            {
                return null;
            }
        }

        [HttpDelete]
        [Route("api/projects")]
        [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
        public int Delete(int ProjectID)
        {
            
            Project existingProject = db.Projects.Where(temp => temp.ProjectID == ProjectID).FirstOrDefault();
            if (existingProject != null)
            {
                db.Projects.Remove(existingProject);
                db.SaveChanges();
                return ProjectID;
            }
            else
            {
                return -1;
            }
        }
    }

}


