<#assign deuiaction=item.getPSDEUIAction()>
{text: '${item.caption}',srftag:{id:'${deuiaction.id}',sysid:'${deuiaction.getPSSysDEUIActionId()}'}, handler: 'onClickToolbarItem'}