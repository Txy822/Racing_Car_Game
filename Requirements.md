# Requirements Analysis

## Functional Requirements

#### 1. Networking / Database
- local database
    - verify login info
    - Update leaderboard
        - fastest time from users.
        - update to database.  
    - Manage multiplayer lobbies
        - Thread managing each one
    - gameplay
        - latency
        - synchronization between clients

- create an account with username/login infomration
    - saved in database.
    - each user has an unique ID number.
    - Name/Password/Fatest Race time within specific track (top 3 race time) for time trial. 



#### 3. Gameplay

- 1-4 race cars controllable by either Users or AI
    - Car physics
        - acceleration/decleration
        - collision
            - car with stage collision
            - car with car collision
        - max speed
    - Stage physics
        - collision
            - against wall
            - bounce off
        - speed modifiers
            - normal track
            - gravel
            - wall
    - Modifier
        - Powerups
    - AI 
        - Difficulty
        => AI section
    - UI => pause/play, start/end race
    
- Arcade Mode (generated map)
    - Difficulty = > AI
    - Map general 

- Time trial mode
    - Difficulty = > AI


- General race
    - Difficulty = > AI
    - Multiplayer / AI = > Networking / AI
        - Generate 3 set map
        - follows 1 - 4 race car general.
        - counts time, position of cars leader boards.
- networking

#### 4. Artificial Intelligence
- Follows game physics
- Path finding
- Difficulties
    - Handicap 
    - Modify maximum speed, accleration
    - create the best AI, then make it worse
    - Randomization of cars within each difficulty levels
    - 3 levels of difficulty
        - Easy, Normal, Hard

#### 5. User Interface
- Main Menu
- Log-on Screen
- Settings Screen
- Leaderboard
- Multiplayer Lobbies
    - Lobby Screen
- End race Screen
- Pause screen
- Selection screen
- Game mode
    - current car speed
    - time trial
        - record time upload to database
        - minimap
    - general
        - display lap time per player
        - player posistion
        - minimap
    - arcade
        - player position


#### 6. Audio
- Race Music
- Countdown Music
- Physics Effect
    - Collision
    - Accerlation
    - Break
- End race effect






## Non Functional Requirements

#### To add:
- The app will upload user data to the server within 3 seconds
- The app will fetch user data from server within 3 seconds (based on >1MB/s download speed)

#### 1.Security
- The app will implement hybrid cryptography for secure data transfer between cloud server and local app process used by user.
    - The app must ensure data communications with external sources are either local or also secure. Unless necessary, all communications are primarily between local app and cloud server.
        - Tracker applications and devices will be accessed without using any interaction between local app and cloud server.
        - Tracker devices that rely on Bluetooth technology must also encrypt all transfer of communication between the device and the app.
        - Fetching calendar/deadlines information from Bham API and Canvas API rely on the security of the respective systems.
        - Credentials must be encrypted before transferring to API to avoid access-points into app or respective accounts of Bham and Canvas.

#### 2.Reliability
- The app should be designed to account for possible errors and failures in the components and attempt to address it without hurting user experience or minimising it.
    - The AI should be designed and stress tested in mind to handle any and all changes in the app components.
    - The app should be designed to sync information from cloud coming from older versions of the app and updating the local app accordingly.
    - Conversely, components within the local app must be designed so that, once newer versions of the app are introduced with changes to its components, it must be able to interpret information from older versions of app without error. The app will only modify information based on changes to component between versions.
- The cloud server should be designed with redundancy in mind, syncing with back-up server(s) after every set uploads from all users to account for possible failure from either servers.

#### 3.Scalability
- The app should be constructed that an increase of users does not adversely affect the user experience of the individual user.
    - Be designed that additional servers can be added or removed seamlessly and proportionally handle requests from users as user base increases or decreases.
    - Load balancers control server traffic to prevent server overload. Cloud server maintains control over sync request from local app, and can issue earlier sync requests to reduce expected spikes in data due to set upload time, or divert sync request to additional identical servers.

#### 4.Efficiency
- Actions the users may take must take a minimal amount of processing time of 1 second within the app.
    - Communications to external API i.e. Canvas or Bham must not add on significant waiting time than based on the users internet connection speed.
    - All background tasks should be done in the background and thus can take more time, but must finish as soon as possible
        - Server syncs should compare information between local app and cloud server to upload the smallest possible data.
        - Behavioural Analysis AI should minimise time complexity given increase in information coming in to efficiently create recommendation models.
            - The BAI should compare incoming information with current one that it is actively using, and only pass new information to update recommendation models, rather than recreating them each time.

#### 5.Maintainability
- The app should be able to operate with minimal human oversight
    - The BAI should be able to run autonomously without direct user interaction and run effectively with any given amount of information collected.
    - The cloud servers should be able to operate either indecently from other servers in case of required server maintenance and updates.
    - The cloud server should be set up to accept sync requests from older versions of the app as it only handles incoming packages of user information.

#### 6.Accessibility and Usability
- All of the app should be able to be used effectively with minimal instructions, and is intuitive to the user. The app should also be accessible by people with disabilities.
    - The design of the UI must take into account of colour blindness so that people with them are not confused.
    - The questions asked by the questionnaire should be short, understandable, concise.
    - General word descriptors of the app functionalities should be short, understandable, concise.
    - Aim to display the app in mostly visuals instead of words.
