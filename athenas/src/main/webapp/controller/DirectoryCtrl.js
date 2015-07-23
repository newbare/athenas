var directoryApp = angular.module('directoryApp', []);

directoryApp.controller('DirectoryCtrl', function($scope, $http, $window) {
    
    // You can set the server ip in the url like this
    // http://localhost/path/index.html?ip=192.168.0.1
    var ip = QueryString()['ip'] ? QueryString()['ip'] : "localhost";
    var urlAll = "http://" + ip + ":8080/athenas/rest/members/all";
    var urlMember = "http://" + ip + ":8080/athenas/rest/members/id/";

    // For Cross Domain request
    $http.defaults.useXDomain = true;

    $scope.formData = {};


    $scope.loadDirectory = function() {

        $scope.filtreName = "";  // search reset 

        $http.get(urlAll)
        .success(function(data) {
            $scope.directory = data;

        })
        .error(function(data, status) {
            console.log(data, status);
        });
    };

    /**
     * When a click is triggered on a Member from the directory list
     * 
     * @param int index
     * @param json Member
     */
    $scope.setFormMember = function(index, member) {
        $scope.formData.id = index;
        $scope.formData.name = member.name;
        $scope.formData.email = member.email;
        $scope.formData.phoneNumber = member.phoneNumber;
    };

    /**
     * Send a new Member or a modified to the server for proceed to a save
     * 
     * @param json Member
     */
    $scope.editMember = function(member) {
        
        var urlForServer = urlMember + ($scope.formData.id >= 0  ? $scope.formData.id : "");
        var httpMethode = $scope.formData.id >= 0 ? 'POST' : 'PUT';
        
        $http({
            method: httpMethode,
            url: urlForServer,
            data: $scope.formData,
            headers: {'Content-Type': 'application/json'}
        })
        .success(function(data) {
            if(data == 1) {
                if(httpMethode == 'PUT') {
                    $scope.formData.id = $scope.directory.length;
                }

                // angular.copy allows you to copy data to avoid to keep a link with the scope
                $scope.directory[$scope.formData.id] = angular.copy($scope.formData);
            }
        });
    };
    
    $scope.removeMember = function(member){
        
        var indexMemberToKill = getIndexByMember(member);
          
        var urlForPost = urlMember + indexMemberToKill;
       
        $http({
            method: 'DELETE',
            url: urlForPost,
            headers: {'Content-Type': 'application/json'}
        })
        .success(function(data) {
            if(data == 1) {
                // remove a Member in the directory array
                $scope.directory.splice(indexMemberToKill, 1);
            }
        });
    };

    /**
     * Find a Member in the directory and retrun his index
     * 
     * @param {json} Member
     * @returns int
     */
    var getIndexByMember = function(member) {
        for(index in $scope.directory) {
            if($scope.directory[index] == member) {
                return member.id;
            }
        }
    };
    
    // Init directory
    $scope.loadDirectory();
});