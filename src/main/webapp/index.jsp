<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/style.css">
    <title>Jenkin CI/CD Automation</title>
</head>
<body>
<img src="https://iconape.com/wp-content/png_logo_vector/cib-jenkins.png" alt="Girl in a jacket" width="600" height="600">
    <div class="m_body">
        <h1 id="welcome">Welcome</h1>
        <p>Click on This Button To View team</p>
       
        <!-- Trigger/Open The Modal -->
        <button id="myBtn" class="button-19">Team</button>
    </div>
        <!-- The Modal -->
        <div id="myModal" class="modal">
    
            <!-- Modal content -->
            <div class="modal-content">
                <span class="close">&times;</span>
                <h1>Rabbit and tortoise technology solution.</h1>
                <h2>We Are Deploy this Project Throught CI/CD Jenkins.</h2>
               <table>
  <tr>
    <th>Name</th>
    <th>Designation</th>
    <th>E-mail</th>
  </tr>
  <tr>
    <td>Aditya Sankpal</td>
    <td>Devops</td>
    <td>a.sankpal@rnt.ai</td>
  </tr>
  <tr>
    <td>Vinit Patil</td>
    <td>Devops</td>
    <td>v.patil@rnt.ai</td>
  </tr>
  <tr>
    <td>Kirti Bafna</td>
    <td>Devops</td>
    <td>k.bafna@rnt.ai</td>
  </tr>
  <tr>
    <td>Ramesh Jangir</td>
    <td>Devops</td>
    <td>r.jangir@rnt.ai</td>
  </tr>
  <tr>
    <td>Shubham Bangre</td>
    <td>Devops</td>
    <td>s.bangre@rnt.ai</td>
  </tr>
  <tr>
    <td>Akshay Pawar</td>
    <td>Devops</td>
    <td>ak.pawar@rnt.ai</td>
  </tr>
  <tr>
    <td>Vinmay Parkhi</td>
    <td>Devops</td>
    <td>v.parkhi@rnt.ai</td>
  </tr>
  <tr>
    <td>samba sir </td>
    <td>CEO</dt>
    <td>samba@rnt.ai</td>
    </td>
</table>
                
    
            </div>
    
        </div>
        
        
        
    
        <script>
            // Get the modal
            var modal = document.getElementById("myModal");
            var modal_2=document.getElementById("myModal_2");
            // Get the button that opens the modal
            var btn = document.getElementById("myBtn");
            var btn_2 = document.getElementById("myBtn_2");
            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];
            var span_2 = document.getElementsByClassName("close_2")[0];   
            // When the user clicks the button, open the modal 
            btn.onclick = function () {
                modal.style.display = "block";
            }



    
            // When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            }
            
            
    
            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
            function swal_call(){
                
            }
    
        </script>
</body>
</html>
