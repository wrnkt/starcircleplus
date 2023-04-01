import SwiftUI
import RealmSwift

struct ItemRow: View {
    @ObservedRealmObject var item: Item
    
    var body: some View {
        NavigationLink(destination: ItemDetail(item: item)) {
            Text(item.textContent)
            Spacer()
            if item.isComplete {
                Image(systemName: "checkmark")
                    .foregroundColor(.blue)
                    .padding(.trailing, 10)
            }
            if item.owner_id == app.currentUser?.id {
                Text("(mine)")
            }
        }
    }
}
