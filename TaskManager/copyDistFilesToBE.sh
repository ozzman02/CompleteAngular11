#!/bin/zsh

distFolderPath=/Users/ovenegas/Git/CompleteAngular11/TaskManager/dist/task-manager/*
wwwrootFolderPath=/Users/ovenegas/Git/CompleteAngular11/MvcTaskManager-AspNetCore2.1/MvcTaskManager/wwwroot
indexHtmlFilePath=/Users/ovenegas/Git/CompleteAngular11/MvcTaskManager-AspNetCore2.1/MvcTaskManager/wwwroot/index.html
indexCshtmlFilePath=/Users/ovenegas/Git/CompleteAngular11/MvcTaskManager-AspNetCore2.1/MvcTaskManager/Views/Home/Index.cshtml

cp -R $distFolderPath $wwwrootFolderPath
cp $indexHtmlFilePath $indexCshtmlFilePath


echo "Copying files from ${distFolderPath} to ${wwwrootFolderPath} complete."
echo "Moving content from index.html to Index.cshtml complete."