import RealmSwift
import UIKit

class Item: Object, ObjectKeyIdentifiable {
    @Persisted(primaryKey: true) var _id: ObjectId
    @Persisted var owner_id: String
    @Persisted var type: EntryType
    @Persisted var isComplete = false
    @Persisted var textContent: String
    @Persisted var tags: List<Tag>
 
}

extension Item {
    static let testNote = Item(value: ["type": EntryType.note, "isComplete": false, "textContent": "This is a note Entry."])
    static let testTodo = Item(value: ["type": EntryType.todo, "isComplete": false, "textContent": "This is a todo Entry."])
    static let testSpecial = Item(value: ["type": EntryType.special, "isComplete": false, "textContent": "This is a special Entry."])
}

enum EntryType: Int, PersistableEnum, CaseIterable {
    case todo = 0
    case special = 1
    case note = 2
    
    
    var description: String {
        switch self {
        case .todo: return "Circle"
        case .special: return "Star"
        case .note: return "Plus"
        }
    }
}

class Tag: Object, ObjectKeyIdentifiable {
    @Persisted(primaryKey: true) var _id: ObjectId
    @Persisted var owner_id: String
    @Persisted var name: String
    @Persisted(originProperty: "tags") var item: LinkingObjects<Item>
}

