import RealmSwift
import UIKit

class Item: Object, ObjectKeyIdentifiable {
    @Persisted(primaryKey: true) var _id: ObjectId
    @Persisted var owner_id: String
    @Persisted var type: EntryType
    @Persisted var isComplete = false
    @Persisted var textContent: String
    @Persisted var tags: List<Tag>
 
    //// TEST ////
    static func testNote() -> Item {
        let testNote = Item()
        // testNote._id = 0
        // testNote.owner_id = ""
        testNote.type = EntryType.note
        testNote.isComplete = false
        testNote.textContent = "This is a note Entry."
        return testNote
    }
    
    static func testTodo() -> Item {
        let testTodo = Item()
        // testTodo._id = 0
        // testTodo.owner_id = ""
        testTodo.type = EntryType.todo
        testTodo.isComplete = false
        testTodo.textContent = "This is a todo Entry."
        return testTodo
    }
    
    static func testSpecial() -> Item {
        let testSpecial = Item()
        // testSpecial._id = 0
        // testSpecial.owner_id = ""
        testSpecial.type = EntryType.special
        testSpecial.isComplete = false
        testSpecial.textContent = "This is a special Entry."
        return testSpecial
    }
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

