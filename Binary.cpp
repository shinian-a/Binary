#include <iostream>
#include <fstream>
using namespace std;
class Person{
public:
    string name;
    int age;
    Person(string n,int a){
        name = n;
        age = a;
    }
    Person(){};
    ~Person(){};
    void toString(){
        cout<<"name:"<<name<<" age:"<<age<<endl;
    }
};
void writeB(){
    //导入头文件
    //创建对象
    ofstream ofs;
    //打开文件，判断是否打开
    ofs.open("/sdcard/二进制执行成功.txt",ios::out|ios::binary);
    if(ofs.is_open()){
        //写入文件
        Person p("true",200);
        ofs.write((const char*)&p,sizeof (p));
    } else{
        cout<<"open file defluat"<<endl;
    }
    //关闭流
    ofs.close();
}
void readB(){
    ifstream ifs;
    ifs.open("/sdcard/二进制执行成功.txt",ios::in|ios::binary);
    if(ifs.is_open()){
        Person p;
        ifs.read((char *)&p,sizeof (p));
        //p.toString();
    } else{
        cout<<"open file defluat"<<endl;
    }
    ifs.close();
}
int main() {
    writeB();
    readB();
    return 0;
}