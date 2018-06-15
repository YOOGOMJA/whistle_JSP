<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css"
    href="/Whislte/assets/css/menu.css">
<script type='text/javascript'
    src="/Whislte/assets/scripts/controllers/menu.ctrl.js"></script>


<!-- <nav class="navbar" ng-controller='menuCtrl'>
    <md-content>
        <md-button class="md-icon-button md-primary" aria-label="Settings">
            
        </md-button>
        <a class="navbar-brand" href="#">Whistle</a>      
    </md-content>
  

</nav> -->
<div ng-controller='menuCtrl' class='nav-menu'>
    <nav class='nav' style='z-index:10'>
        <md-button ng-click='fn.evt.toggle()' class='md-icon-button md-primary' aria-label="Menu">
            <md-icon md-svg-icon="/Whislte/assets/images/icons/menu_button_white.svg">
            </md-icon> 
        </md-button>
    </nav>

    
    <section layout="row" flex ng-cloak>
        <md-sidenav class="md-sidenav-left"
            md-component-id="closeEventsDisabled" md-whiteframe="4"
            md-disable-close-events > <md-toolbar
            class="md-theme-indigo">
        <div class='user-info'>
            <md-button ng-click='fn.evt.toggle()' class='md-icon-button md-primary btn-close' aria-label="Menu">
                <md-icon md-svg-icon="/Whislte/assets/images/icons/close.svg">
                </md-icon> 
            </md-button>
            <div class='user-info-container'>
                <md-icon md-svg-icon="/Whislte/assets/images/icons/person_man.svg"></md-icon>
            </div>
            <div>
                <h1 class='md-title' style="text-align:center">
                <%=session.getAttribute("userName") %>
                </h1>
            </div>
        </div>
        </md-toolbar> 
        <md-content>
            <md-list class='nav-items'>
                <div ng-repeat='item in mod.links'>
                    <md-list-item ng-click='fn.evt.go(item.link)' class='md-primary'>
                        <p ng-bind='item.title'></p>
                    </md-list-item>
                    <md-divider></md-divider>
                </div>
                <div>
                    <md-list-item ng-click='fn.evt.logout()' class='md-primary'>
                        <p>Logout</p>
                    </md-list-item>
                    <md-divider></md-divider>
                </div>
            </md-list> 
        </md-content> 
        </md-sidenav>
    </section>

</div>
