import React from 'react';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';
import { AiOutlineLogout } from "react-icons/ai";

export const SidebarData = [
  {
    title: 'Home',
    path: '/home',
    icon: <AiIcons.AiFillHome />,
    cName: 'nav-text'
  },
  {
    title: 'Reports',
    path: '/reports',
    icon: <IoIcons.IoIosPaper />,
    cName: 'nav-text'
  },
  {
    title: 'Categories',
    path: '/category',
    icon: <FaIcons.FaCartPlus />,
    cName: 'nav-text'
  },
//   {
//     title: 'Support',
//     path: '/support',
//     icon: <IoIcons.IoMdHelpCircle />,
//     cName: 'nav-text'
//   },
  {
    title: 'Logout',
    path: '/logout',
    icon: <AiOutlineLogout />,
    cName: 'nav-text'
  }
];