/* Auto-generated by genmsg_cpp for file /home/precise/namo/trunk/new_ros_stuff/magic_robot_fuerte/custom_msgs/msg/encoder_msg.msg */
#ifndef CUSTOM_MSGS_MESSAGE_ENCODER_MSG_H
#define CUSTOM_MSGS_MESSAGE_ENCODER_MSG_H
#include <string>
#include <vector>
#include <map>
#include <ostream>
#include "ros/serialization.h"
#include "ros/builtin_message_traits.h"
#include "ros/message_operations.h"
#include "ros/time.h"

#include "ros/macros.h"

#include "ros/assert.h"

#include "std_msgs/Header.h"

namespace custom_msgs
{
template <class ContainerAllocator>
struct encoder_msg_ {
  typedef encoder_msg_<ContainerAllocator> Type;

  encoder_msg_()
  : header()
  , fl_tick(0)
  , fr_tick(0)
  , rl_tick(0)
  , rr_tick(0)
  , fl_vel_meters(0.0)
  , fr_vel_meters(0.0)
  , rl_vel_meters(0.0)
  , rr_vel_meters(0.0)
  {
  }

  encoder_msg_(const ContainerAllocator& _alloc)
  : header(_alloc)
  , fl_tick(0)
  , fr_tick(0)
  , rl_tick(0)
  , rr_tick(0)
  , fl_vel_meters(0.0)
  , fr_vel_meters(0.0)
  , rl_vel_meters(0.0)
  , rr_vel_meters(0.0)
  {
  }

  typedef  ::std_msgs::Header_<ContainerAllocator>  _header_type;
   ::std_msgs::Header_<ContainerAllocator>  header;

  typedef int64_t _fl_tick_type;
  int64_t fl_tick;

  typedef int64_t _fr_tick_type;
  int64_t fr_tick;

  typedef int64_t _rl_tick_type;
  int64_t rl_tick;

  typedef int64_t _rr_tick_type;
  int64_t rr_tick;

  typedef double _fl_vel_meters_type;
  double fl_vel_meters;

  typedef double _fr_vel_meters_type;
  double fr_vel_meters;

  typedef double _rl_vel_meters_type;
  double rl_vel_meters;

  typedef double _rr_vel_meters_type;
  double rr_vel_meters;


  typedef boost::shared_ptr< ::custom_msgs::encoder_msg_<ContainerAllocator> > Ptr;
  typedef boost::shared_ptr< ::custom_msgs::encoder_msg_<ContainerAllocator>  const> ConstPtr;
  boost::shared_ptr<std::map<std::string, std::string> > __connection_header;
}; // struct encoder_msg
typedef  ::custom_msgs::encoder_msg_<std::allocator<void> > encoder_msg;

typedef boost::shared_ptr< ::custom_msgs::encoder_msg> encoder_msgPtr;
typedef boost::shared_ptr< ::custom_msgs::encoder_msg const> encoder_msgConstPtr;


template<typename ContainerAllocator>
std::ostream& operator<<(std::ostream& s, const  ::custom_msgs::encoder_msg_<ContainerAllocator> & v)
{
  ros::message_operations::Printer< ::custom_msgs::encoder_msg_<ContainerAllocator> >::stream(s, "", v);
  return s;}

} // namespace custom_msgs

namespace ros
{
namespace message_traits
{
template<class ContainerAllocator> struct IsMessage< ::custom_msgs::encoder_msg_<ContainerAllocator> > : public TrueType {};
template<class ContainerAllocator> struct IsMessage< ::custom_msgs::encoder_msg_<ContainerAllocator>  const> : public TrueType {};
template<class ContainerAllocator>
struct MD5Sum< ::custom_msgs::encoder_msg_<ContainerAllocator> > {
  static const char* value() 
  {
    return "0e57cf0601577021e58b1171e4fa6c90";
  }

  static const char* value(const  ::custom_msgs::encoder_msg_<ContainerAllocator> &) { return value(); } 
  static const uint64_t static_value1 = 0x0e57cf0601577021ULL;
  static const uint64_t static_value2 = 0xe58b1171e4fa6c90ULL;
};

template<class ContainerAllocator>
struct DataType< ::custom_msgs::encoder_msg_<ContainerAllocator> > {
  static const char* value() 
  {
    return "custom_msgs/encoder_msg";
  }

  static const char* value(const  ::custom_msgs::encoder_msg_<ContainerAllocator> &) { return value(); } 
};

template<class ContainerAllocator>
struct Definition< ::custom_msgs::encoder_msg_<ContainerAllocator> > {
  static const char* value() 
  {
    return "Header header\n\
int64 fl_tick\n\
int64 fr_tick\n\
int64 rl_tick\n\
int64 rr_tick\n\
float64 fl_vel_meters\n\
float64 fr_vel_meters\n\
float64 rl_vel_meters\n\
float64 rr_vel_meters\n\
\n\
================================================================================\n\
MSG: std_msgs/Header\n\
# Standard metadata for higher-level stamped data types.\n\
# This is generally used to communicate timestamped data \n\
# in a particular coordinate frame.\n\
# \n\
# sequence ID: consecutively increasing ID \n\
uint32 seq\n\
#Two-integer timestamp that is expressed as:\n\
# * stamp.secs: seconds (stamp_secs) since epoch\n\
# * stamp.nsecs: nanoseconds since stamp_secs\n\
# time-handling sugar is provided by the client library\n\
time stamp\n\
#Frame this data is associated with\n\
# 0: no frame\n\
# 1: global frame\n\
string frame_id\n\
\n\
";
  }

  static const char* value(const  ::custom_msgs::encoder_msg_<ContainerAllocator> &) { return value(); } 
};

template<class ContainerAllocator> struct HasHeader< ::custom_msgs::encoder_msg_<ContainerAllocator> > : public TrueType {};
template<class ContainerAllocator> struct HasHeader< const ::custom_msgs::encoder_msg_<ContainerAllocator> > : public TrueType {};
} // namespace message_traits
} // namespace ros

namespace ros
{
namespace serialization
{

template<class ContainerAllocator> struct Serializer< ::custom_msgs::encoder_msg_<ContainerAllocator> >
{
  template<typename Stream, typename T> inline static void allInOne(Stream& stream, T m)
  {
    stream.next(m.header);
    stream.next(m.fl_tick);
    stream.next(m.fr_tick);
    stream.next(m.rl_tick);
    stream.next(m.rr_tick);
    stream.next(m.fl_vel_meters);
    stream.next(m.fr_vel_meters);
    stream.next(m.rl_vel_meters);
    stream.next(m.rr_vel_meters);
  }

  ROS_DECLARE_ALLINONE_SERIALIZER;
}; // struct encoder_msg_
} // namespace serialization
} // namespace ros

namespace ros
{
namespace message_operations
{

template<class ContainerAllocator>
struct Printer< ::custom_msgs::encoder_msg_<ContainerAllocator> >
{
  template<typename Stream> static void stream(Stream& s, const std::string& indent, const  ::custom_msgs::encoder_msg_<ContainerAllocator> & v) 
  {
    s << indent << "header: ";
s << std::endl;
    Printer< ::std_msgs::Header_<ContainerAllocator> >::stream(s, indent + "  ", v.header);
    s << indent << "fl_tick: ";
    Printer<int64_t>::stream(s, indent + "  ", v.fl_tick);
    s << indent << "fr_tick: ";
    Printer<int64_t>::stream(s, indent + "  ", v.fr_tick);
    s << indent << "rl_tick: ";
    Printer<int64_t>::stream(s, indent + "  ", v.rl_tick);
    s << indent << "rr_tick: ";
    Printer<int64_t>::stream(s, indent + "  ", v.rr_tick);
    s << indent << "fl_vel_meters: ";
    Printer<double>::stream(s, indent + "  ", v.fl_vel_meters);
    s << indent << "fr_vel_meters: ";
    Printer<double>::stream(s, indent + "  ", v.fr_vel_meters);
    s << indent << "rl_vel_meters: ";
    Printer<double>::stream(s, indent + "  ", v.rl_vel_meters);
    s << indent << "rr_vel_meters: ";
    Printer<double>::stream(s, indent + "  ", v.rr_vel_meters);
  }
};


} // namespace message_operations
} // namespace ros

#endif // CUSTOM_MSGS_MESSAGE_ENCODER_MSG_H

