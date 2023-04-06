import SwiftUI
import RealmSwift

struct ItemRow: View {
    @ObservedRealmObject var item: Item
    
    var body: some View {
        NavigationLink(destination: ItemDetail(item: item)) {
            TypeDisplay(item: item)
            Spacer()
            Text(item.textContent)
            Spacer()
            
            if item.owner_id == app.currentUser?.id {
                //Text("(mine)")
            }
        }
    }
}

struct TypeDisplay: View {
    @ObservedRealmObject var item: Item
    
    var body: some View {
        switch item.type {
            case EntryType.todo:
                if item.isComplete {
                    Image(systemName: "checkmark.circle")
                } else {
                    Image(systemName: "circle")
                }
            case EntryType.note:
                Image(systemName: "plus")
            case EntryType.special:
                Image(systemName: "star.fill")
        }
    }
}

struct Previews_ItemRow_Previews: PreviewProvider {
    static var previews: some View {
        /*@START_MENU_TOKEN@*/Text("Hello, World!")/*@END_MENU_TOKEN@*/
    }
}
