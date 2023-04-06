import SwiftUI
import RealmSwift

struct ItemRow: View {
    @ObservedRealmObject var item: Item
    
    var body: some View {
        HStack {
            TypeDisplay(item: item)
                .padding(.trailing, 20)
            NavigationLink(destination: ItemDetail(item: item)) {
                Spacer()
                Text(item.textContent)
                Spacer()
                
                if item.owner_id == app.currentUser?.id {
                    //Text("(mine)")
                }
            }
        }
    }
}

struct TypeDisplay: View {
    @ObservedRealmObject var item: Item
    
    let todoTypeCheckedIcon = Image(systemName: "checkmark.circle")
    let todoTypeUncheckedIcon = Image(systemName: "circle")
    let noteTypeIcon = Image(systemName: "plus")
    let specialTypeIcon = Image(systemName: "star.fill")
    
    var body: some View {
        switch item.type {
            case EntryType.todo:
                if item.isComplete {
                    todoTypeCheckedIcon
                       .resizable()
                       .frame(width: 32.0, height: 32.0)
                } else {
                    todoTypeUncheckedIcon
                       .resizable()
                       .frame(width: 32.0, height: 32.0)
                }
            case EntryType.note:
                noteTypeIcon
                    .resizable()
                    .frame(width: 32.0, height: 32.0)
            case EntryType.special:
                specialTypeIcon
                .resizable()
                   .frame(width: 32.0, height: 32.0)
        }
    }
}

struct Previews_ItemRow_Previews: PreviewProvider {
    static var previews: some View {
        /*@START_MENU_TOKEN@*/Text("Hello, World!")/*@END_MENU_TOKEN@*/
    }
}
