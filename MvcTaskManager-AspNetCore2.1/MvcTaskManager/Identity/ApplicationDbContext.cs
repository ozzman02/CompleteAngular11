using System;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using MvcTaskManager.Models;

namespace MvcTaskManager.Identity
{
    /* Extends IdentityDbContext class and contains other DbSet's for all the database tables that you want to interact with */
    public class ApplicationDbContext : IdentityDbContext<ApplicationUser, ApplicationRole, String>
    {
        public ApplicationDbContext(DbContextOptions options) : base(options)
        {
        }

        public DbSet<Project> Projects { get; set; }
        public DbSet<IdentityRole> Roles { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
        }
    }
}

