export default {
    state: {
        submenu: sessionStorage.getItem('submenu'),
        subMenuItem: sessionStorage.getItem('subMenuItem'),
        activeIndex: sessionStorage.getItem("activeIndex")
    },
    mutations: {
        setNamedItem(key, value) {
            sessionStorage.setItem("subMenuItem", value);
            key.subMenuItem = value;
            key.submenu = sessionStorage.getItem('submenu');
        },
        setSubMenu(state, value) {
            sessionStorage.setItem('submenu', value);
        },
        removeActive(state) {
            state.activeIndex = '';
            state.subMenuItem = '';
            state.submenu = '';
            sessionStorage.removeItem('submenu');
            sessionStorage.removeItem('subMenuItem');
            sessionStorage.removeItem("activeIndex");
        }
    },
    actions: {},
    getters: {
        getMenu(state) {
            if (sessionStorage.getItem('submenu') !== null) {
                if (state.submenu !== '')
                    return state.submenu;
                else
                    return sessionStorage.getItem('submenu');
            } else return state.submenu;
        }, getMenuIem(state) {
            if (sessionStorage.getItem('subMenuItem') !== null) {
                if (state.subMenuItem !== '') {
                    return state.subMenuItem;
                } else
                    return sessionStorage.getItem('subMenuItem');
            } else return state.subMenuItem
        },
        getActiveIndex(state) {
            return state.activeIndex
        }
    }
}