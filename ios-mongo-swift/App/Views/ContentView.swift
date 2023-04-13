import SwiftUI
import RealmSwift

struct ContentView: View {
    @ObservedObject var app: RealmSwift.App
    @EnvironmentObject var errorHandler: ErrorHandler
    
    @Environment(\.colorScheme) var colorScheme

    var body: some View {
        if let user = app.currentUser {
            // Setup configuraton so user initially subscribes to their own tasks
            let config = user.flexibleSyncConfiguration(initialSubscriptions: { subs in
                subs.remove(named: Constants.allItems)
                if let _ = subs.first(named: Constants.myItems) {
                    // Existing subscription found - do nothing
                    return
                } else {
                    // No subscription - create it
                    subs.append(QuerySubscription<Item>(name: Constants.myItems) {
                        $0.owner_id == user.id
                    })
                }
            })
            OpenRealmView(user: user)
            .preferredColorScheme(.dark)
            .background(colorScheme == .dark ? Color(UIColor.darkGray) : Color(UIColor.lightGray))
                // Store configuration in the environment to be opened in next view
                .environment(\.realmConfiguration, config)
        } else {
            // If there is no user logged in, show the login view.
            LoginView()
            .preferredColorScheme(.dark)
        }
    }
}

struct Previews_ContentView_Previews: PreviewProvider {
    static var previews: some View {
        /*@START_MENU_TOKEN@*/Text("Hello, World!")/*@END_MENU_TOKEN@*/
    }
}
