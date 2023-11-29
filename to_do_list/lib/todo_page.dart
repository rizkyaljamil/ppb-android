import 'package:flutter/material.dart';
import 'package:to_do_list/database_helper.dart';
import 'package:to_do_list/todo.dart';

class TodoPage extends StatelessWidget {
  const TodoPage({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: TodoList(),
    );
  }
}

class TodoList extends StatefulWidget {
  const TodoList({
    super.key,
  });

  @override
  State<StatefulWidget> createState() => _TodoList();
}

class _TodoList extends State<TodoList> {
  final TextEditingController _namaCtrl = TextEditingController();
  final TextEditingController _descCtrl = TextEditingController();
  final TextEditingController _searchCtrl = TextEditingController();
  List<Todo> todoList = [];

  final dbHelper = DatabaseHelper();

  @override
  void initState() {
    super.initState();
    refreshList();
  }

  void refreshList() async {
    final todos = await dbHelper.getAllTodos();
    setState(() {
      todoList = todos;
    });
  }

  void updateItem(int index, bool done) async {
    todoList[index].done = done;
    await dbHelper.updateTodo(todoList[index]);
    refreshList();
  }

  void deleteItem(int id) async {
    // todoList.removeAt(index);
    await dbHelper.deleteTodo(id);
    refreshList();
  }

  void addItem() async {
    await dbHelper.addTodo(Todo(_namaCtrl.text, _descCtrl.text));

    refreshList();

    _namaCtrl.text = "";
    _descCtrl.text = "";
  }

  void searchTodo() async {
    String teks = _searchCtrl.text.trim();
    List<Todo> todos = [];

    if (teks.isEmpty){
      todos = await dbHelper.getAllTodos();
    } else {
      todos = await dbHelper.searchTodo(teks);
    }

    setState(() {
      todoList = todos;
    });
  }

  void showForm() {
    showDialog(
        context: context,
        builder: (context) => AlertDialog(
              insetPadding: const EdgeInsets.all(20),
              title: const Text("Tambah"),
              actions: [
                ElevatedButton(
                    onPressed: () {
                      Navigator.pop(context);
                    },
                    child: const Text("Tutup")),
                ElevatedButton(
                    onPressed: () {
                      addItem();
                      Navigator.pop(context);
                    },
                    child: const Text("Tambah"))
              ],
              content: Container(
                width: MediaQuery.of(context).size.width,
                height: 200,
                child: Column(
                  children: [
                    TextField(
                      controller: _namaCtrl,
                      decoration: const InputDecoration(hintText: "Nama Todo"),
                    ),
                    TextField(
                      controller: _descCtrl,
                      decoration: const InputDecoration(hintText: "Deskripsi Todo"),
                    ),
                  ],
                ),
              ),
            ));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Aplikasi Todo List"),
          centerTitle: true,
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            showForm();
          },
          child: const Icon(Icons.add),
        ),
        body: Column(
          children: [
            Container(
              margin: const EdgeInsets.symmetric(horizontal: 20),
              child: TextField(
                controller: _searchCtrl,
                onChanged: (_){
                  searchTodo();
                },
                decoration: const InputDecoration(
                    hintText: 'Cari apa bro?',
                    prefixIcon: Icon(Icons.search),
                    border: OutlineInputBorder()),
              ),
            ),
            Expanded(
                child: ListView.builder(
                    itemCount: todoList.length,
                    itemBuilder: (context, index) {
                      return ListTile(
                        leading: todoList[index].done
                            ? IconButton(
                                icon: const Icon(Icons.check_circle),
                                onPressed: () {
                                  updateItem(index, !todoList[index].done);
                                },
                              )
                            : IconButton(
                                icon: const Icon(Icons.radio_button_unchecked),
                                onPressed: () {
                                  updateItem(index, !todoList[index].done);
                                },
                              ),
                        title: Text(todoList[index].nama),
                        subtitle: Text(todoList[index].deskripsi),
                        trailing: IconButton(
                          icon: const Icon(Icons.delete),
                          onPressed: () {
                            deleteItem(todoList[index].id ?? 0);
                          },
                        ),
                      );
                    }))
          ],
        ));
  }
}
